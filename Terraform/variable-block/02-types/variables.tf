variable "project_id" {
  type        = string
  description = "GCP project ID"
  default = "prod-gcp-2026"
}

variable "region" {
  type        = string
  default     = "us-central1"
}

variable "zone" {
  type        = string
  default     = "us-central1-a"
}

variable "vpc_name" {
  type        = string
  default     = "var-type-vpc"
}

variable "subnet_name" {
  type        = string
  default     = "var-type-subnet"
}

variable "subnet_cidr" {
  type        = string
  default     = "10.4.0.0/16"
}

variable "vm_name" {
  type        = string
  default     = "var-type-webserver"
}

variable "machine_type" {
  type        = string
  default     = "e2-micro"
}

variable "ssh_priority" {
    type = number
    default = 1000
    description = "priority for ssh fw"
}

variable "http_priority" {
  type = number
  default = 1000
  description = "priority for http fw"
} 

variable "dummy_bool" {
  type = bool
  default = false
  description = true # false
}

variable "enable_startup_script" {
  type = bool
  description = "Whether to enable startup script"
  default = false
  # default = true  
}
