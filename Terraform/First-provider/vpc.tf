# Create a VPC
resource "google_compute_network" "vpc1" {
    name = "custom-vpc1"
    description = "vpc created from TF"
    auto_create_subnetworks = false
}


# Create a subnet
# Requirement: subnet-name, region, cidr-range, network name
resource "google_compute_subnetwork" "vpc1-subnet" {
    name = "subnet-x"
    region = "us-central1"
    ip_cidr_range = "10.2.0.0/16"
    # network = "custom-vpc1"
    # Calling implicit dependency
    network = google_compute_network.vpc1.id
}