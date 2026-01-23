# Create SSH Firewall
#Req: fw-name, network, direction, allow/deny, priority, source ranges
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
 }

# Create HTTP firewall
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
   }