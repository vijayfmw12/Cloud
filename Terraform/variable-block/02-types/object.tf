variable "vm_name" {
  type        = string
  default     = "var-type-webserver"
}

variable "machine_type" {
  type        = string
  default     = "e2-micro"
}

variable "zone" {
  type        = string
  default     = "us-central1-a"
}

variable "tags" {
  type = list(string)
  default = ["tag1", "tag2"] 
} 

# Object
variable "vm_configuration" {
  type = Object({
    name = string
    zone = string
    machine_type = string
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