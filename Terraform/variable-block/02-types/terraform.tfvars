enable_startup_script = true  


 vm_list = [
   # First VM.
   # 0
   {
  name = "vm1"
  machine_type = "e2-micro"
  zone = "us-central1-a"
  tags = ["t1","t2"]
   },

   # Second VM.
   # 1
   {
  name = "vm2"
  machine_type = "e2-medium"
  zone = "us-central1-b"
  tags = ["t3","t4"]
   }
]