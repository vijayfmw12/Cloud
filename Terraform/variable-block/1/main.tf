provider "google" {
  project = var.project_id
  region = var.region
}

resource "google_compute_instance" "mw-vm" {
    name = var.name
	machine_type = var.machine_type
	zone = var.vm_zone

	boot_disk {
		initialize_params {
		image = "debian-cloud/debian-11"
		}
	}

	network_interface {
		network = "default"
		access_config  {}
  }
}