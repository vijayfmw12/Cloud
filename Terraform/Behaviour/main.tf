terraform {
# TF version
	required_version = "~>1.14.3"

#Provider Versions
	required_providers {
		google = {
			source = "hashicorp/google"
			version = "~>7.16.0"
		}
	}
}

#Provider Block
provider "google" {
	project = "prod-gcp-2026"
	region  = "us-central1"
	}
#Create a VPC
resource "google_compute_network" "vpc1" {
    name = "custom-vpc1"
    description = "vpc created from TF"
    auto_create_subnetworks = false
}
#Create a subnet (Req: subnet-name, region, cidr-range, network name)
resource "google_compute_subnetwork" "vpc1-subnet" {
    name = "subnet-x"
    region = "us-central1"
    ip_cidr_range = "10.2.0.0/16"
    # network = "custom-vpc1"
    # Calling implicit dependency
    network = google_compute_network.vpc1.id
}
#Create SSH Firewall (Req: fw-name, network, direction, allow/deny, priority, source ranges)
 resource "google_compute_firewall" "tf_ssh" {
    name = "allow-ssh-tf"
    network = google_compute_network.vpc1.id
    direction = "INGRESS"
    allow {
      protocol = "tcp"
      ports = ["22"]
    }
    priority = 1000
    source_ranges = ["0.0.0.0/0"]
    target_tags = ["ssh-nw-tag", "ssh-nw-tag-other"]
  }
#Create HTTP firewall
resource "google_compute_firewall" "tf_http" {
    name = "allow-http-tf"
    network = google_compute_network.vpc1.id
    direction = "INGRESS"
    allow {
        protocol = "tcp"
        ports = ["80"]
        }
   priority = 1000
   source_ranges = ["0.0.0.0/0"]
   target_tags = ["webserver-nw-tag"]
   }
#Create a VM
resource "google_compute_instance" "tf_gce_vm" {
  name = "webserver"
  machine_type= "e2-micro"
  # machine_type= "e2-medium"
  zone = "us-central1-a"
  # zone = "us-central1-b"
    boot_disk {
      initialize_params {
	    image = "debian-cloud/debian-12"
      }
    }
    network_interface {
	  subnetwork = google_compute_subnetwork.vpc1-subnet.id
	  access_config {}
    }
    allow_stopping_for_update = true
  tags = [
    tolist(google_compute_firewall.tf_ssh.target_tags) [1],
    tolist(google_compute_firewall.tf_http.target_tags)[0],
    "new-tag",
    "dba-tag",
    ]
} 
