# Create a VPC
resource "google_compute_network" "vpc1" {
    name = "custom-vpc1"
    description = "vpc created from TF"
    auto_create_subnetworks = "false"
}