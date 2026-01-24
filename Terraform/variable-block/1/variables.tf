variable "machine_type" {
description = "Machine type user for the vm"
type = string
default = "e2-medium"
}


variable "vm_zone" {
description = "The zone in which vm should be created"
type = string
default = "us-central1-a"
}

variable "project_id" {
  type = string
  default = "prod-gcp-2026"
}

variable "region" {
 type = string
 default = "us-central1"
}
