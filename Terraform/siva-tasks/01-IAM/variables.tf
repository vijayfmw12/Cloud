variable "project_id" {
    description = "GCP Proj_ID"
    type = string
}

variable "region" {
    description = "GCP region"
    type = string
    default = "us-central1"
}

variable "iam_user_email" {
    description = "for binding"
    type = string
}
