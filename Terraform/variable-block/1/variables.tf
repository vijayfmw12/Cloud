variable "machine_type" {
description = "Machine type used for the vm"
type = string
}

variable "name" {
  description = "machine name"
  type = string
}

variable "vm_zone" {
description = "The zone in which vm should be created"
type = string
}

variable "project_id" {
  type = string
}

variable "region" {
 type = string
}