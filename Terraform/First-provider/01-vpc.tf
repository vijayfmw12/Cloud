# Resource-1
# create a custom vpc
resource "google_compute_network" "vpc1" {
name = "custom-vpc-tf"
auto_create_subnetworks = false
}

# Resource-2
# create a custom vpc
resource "google_compute_network" "vpc2" {
name = "custom-vpc2-tf"
auto_create_subnetworks = false
}