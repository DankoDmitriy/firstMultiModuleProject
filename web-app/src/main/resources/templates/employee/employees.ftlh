<#import "../parts/common.ftlh" as c>
<@c.page>

<#--From for adding employee-->
    <a class="btn btn-primary mb-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Employer
    </a>

    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" >

                <div class="form-group">
                    <input type="text" class="form-control" name="firstName" placeholder="Enter First Name">
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" name="lastName" placeholder="Enter Last Name">
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" name="salary" placeholder="Enter salary">
                </div>

                <div class="form-group">
                    Birthday
                    <input type="date" class="form-control" name="date" >
                </div>

                <div class="form-group">
                    <select class="form-control" id="exampleFormControlSelect1" name="idDepartment">
                 <#list departments as department>
                        <option value="${department.id}">${department.name}</option>
                </#list>
                    </select>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add new Employer</button>
                </div>
            </form>
        </div>
    </div>


<#--Generating information table-->
    <table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First and Last name</th>
        <th scope="col">Date of birth</th>
        <th scope="col">Salary</th>
        <th scope="col">Department</th>
        <th scope="col">Edit</th>
        <th scope="col">delete</th>
    </tr>
    </thead>
    <tbody>

    <#assign i = 1>
    <#list employees as employee>
        <tr>
        <th scope="row">${i}</th>
        <td><a href="/employees/${employee.id}">${employee.firstName} ${employee.lastName}</a></td>
        <td>${employee.getBirth()}</td>
        <td>${employee.salary}</td>
        <td>${employee.idDepartment.name}</td>
        <td><a href="/employees/edit/${employee.id}">Edit</a></td>
        <td><a href="/employees/delete/${employee.id}">Delete</a></td>
        </tr>
        <#assign i++>
    <#--Work only when array empty -->
    <#else >
        There are no employees in the database or you have problem with connect to REST server.
    </#list>

    </tbody>
    </table>
</@c.page>