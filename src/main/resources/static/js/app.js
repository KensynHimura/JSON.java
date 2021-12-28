    fetch('http://localhost:8080/api/users').then(res => {
        res.json().then(data => {
            if (data.length > 0) {
                let temp = ""
                data.forEach(u => {
                    temp += '<tr>'
                    temp += '<td>' + u.id + '</td>'
                    temp += '<td>' + u.firstName + '</td>'
                    temp += '<td>' + u.lastName + '</td>'
                    temp += '<td>' + u.age + '</td>'
                    temp += '<td>' + u.email + '</td>'
                    temp += '<td>' + u.roles.map(r => r.role) + '</td>'
                    temp += '<td>' + '<button data-bs-toggle="modal" class="btn-info btn btn-primary"' +
                        'data-bs-target="#editStatic" user-id ='+ u.id +'>Edit</button>' + '</td>'
                    temp += '<td>' + '<button data-bs-toggle="modal" class="btn-info btn btn-primary"' +
                        'data-bs-target="#deleteStatic" data-user-id =' + u.id +'>Delete</button>' + '</td>'
                })
                document.getElementById('data').innerHTML = temp
            }
        })
    })



    let myModal = document.getElementById('editStatic')
    let myInput = document.getElementById( 'user-id')
    myModal.addEventListener('shown.bs.modal', e => {
        console.log()
        let userId = e.focus(myInput)
        fetch('http://localhost:8080/api/users/' + userId).then(res => {
            res.json().then(userData => {
                console.log(userData)
                document.getElementById('editId').value = userData.id;
                document.getElementById('editFirstName').value = userData.firstName;
                document.getElementById('editLastName').value = userData.lastName;
                document.getElementById('editAge').value = userData.age;
                document.getElementById('editEmail').value = userData.email;
                document.getElementById('editPassword').value = userData.password;
                fetch('http://localhost:8080/api/users/roles').then(rol => {
                    rol.json().then(roleData => {
                        roleData.map(role => {
                            $('#editRoles').append(
                                $('<option>').text(role.name)
                            )
                        })
                    })
                })
            })
        })
    })

