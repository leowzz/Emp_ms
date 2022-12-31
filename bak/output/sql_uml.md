classDiagram
direction BT
class department {
   varchar(255) dep_name
   int(11) manager_id
   int(11) id
}
class employee {
   varchar(255) name
   varchar(255) passwd
   char(2) sex
   float rate
   date birth
   varchar(255) address
   varchar(20) cardId
   varchar(20) tel
   int(11) dep_id
   int(11) job_id
   int(11) id
}
class job {
   varchar(255) job_name
   int(11) dep_id
   int(11) salary
   int(11) id
}
class manager {
   varchar(20) name
   varchar(20) passwd
   int(11) id
}

department  -->  employee : manager_id:id
department  -->  manager : manager_id:id
employee  -->  department : dep_id:id
employee  -->  job : job_id:id
job  -->  department : dep_id:id
