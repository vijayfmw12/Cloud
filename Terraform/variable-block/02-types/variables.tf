variable "project_id" {
  type        = string
  description = "GCP project ID"
  default = "prod-gcp-2026"
}

variable "region" {
  type        = string
  default     = "us-central1"
}

# variable "zone" {
#  type        = string
#  default     = "us-central1-a"
# }

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

# variable "vm_name" {
#  type        = string
#  default     = "var-type-webserver"
# }

# variable "machine_type" {
#  type        = string
#  default     = "e2-micro"
# }

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
  # default = false
  default = true  
} 

variable "vm_network_tags" {
  type = list(string)
  description = "GCE NW Tags"       // list of strings
  default = ["ssh-nw-tag", "ws-nw-tag"] 
}

variable "environment" {
    type = string
    default = "dev"
}

# variable "machine_types" {
#    type = map(string)
#    default = {
#      dev = "e2-small"
#      stage = "e2-micro" 
#      prod = "n2-standard-2"
#    }
#}

# Object
variable "vm_configuration" {
  type = object({
    name = string
    machine_type = string
    zone = string
    tags = list(string)
  }
)
default = {
  name = "var-type-webserver"
  machine_type = "e2-micro"
  zone = "us-central1-a"
  tags = ["tag1","tag2"]
}
}

variable "vm_list" = [
   # First VM.
   {
  name = "vm1"
  machine_type = "e2-micro"
  zone = "us-central1-a"
  tags = ["t1","t2"]
   }

   # Second VM.
   {
  name = "vm2"
  machine_type = "e2-medium"
  zone = "us-central1-b"
  tags = ["t3","t4"]
   }
]