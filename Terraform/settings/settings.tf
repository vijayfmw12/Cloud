# TF Settings block
terraform {
# TF version
	required_version = "~>1.14.3"

#Provider Versions
	required_providers {
		google = {
			source = "hashicorp/google"
			version = "~>7.14.0"
		}
	}
}