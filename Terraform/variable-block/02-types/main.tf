provider "google" {
  project = var.project_id
  region  = var.region
}

resource "google_compute_network" "tf_vpc" {
  name                    = var.vpc_name
  auto_create_subnetworks = false
}

resource "google_compute_subnetwork" "tf_subnet" {
  name          = var.subnet_name
  region        = var.region
  ip_cidr_range = var.subnet_cidr
  network       = google_compute_network.tf_vpc.id
}

resource "google_compute_firewall" "tf_ssh" {
  name    = "allow-ssh"
  network = google_compute_network.tf_vpc.id
  direction     = "INGRESS"
  priority      = var.ssh_priority
  source_ranges = ["0.0.0.0/0"]

  allow {
    protocol = "tcp"
    ports    = ["22"]
  }

  target_tags = ["ssh-nw-tag"]
}

resource "google_compute_firewall" "tf_http" {
  name    = "allow-http"
  network = google_compute_network.tf_vpc.id
  direction     = "INGRESS"
  priority      = var.http_priority
  source_ranges = ["0.0.0.0/0"]

  allow {
    protocol = "tcp"
    ports    = ["80"]
  }

  target_tags = ["ws-nw-tag"]
}

resource "google_compute_instance" "tf_gce_vm" {
  name         = var.vm_configuration.name
  machine_type = var.vm_configuration.machine_type
  zone         = var.vm_configuration.zone

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-12"
    }
  }

# bool value
# metadata_startup_script = "echo hi > /test.txt
# metadata_startup_script = file("startup.sh")
# metadata_startup_script = file("${path.module}/startup.sh")
metadata_startup_script = var.enable_startup_script ? file("${path.module}/startup.sh") : null 
#file("${path.module}/startup.sh")

    network_interface {
    subnetwork = google_compute_subnetwork.tf_subnet.id
    access_config {}
  }

  # tags = ["ssh-nw-tag", "ws-nw-tag"]
  tags = var.vm_configuration.tags
 }

