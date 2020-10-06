<#import "../parts/common.ftlh" as c>
<@c.page>

    <#--From for adding department-->
    <a class="btn btn-primary mb-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new department
    </a>

    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" >
                <div class="form-group">
                    <input type="text" class="form-control" name="departmentName" placeholder="Enter Department name">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add new department</button>
                </div>
            </form>
        </div>
    </div>


    <#--Generating information table-->
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Department name</th>
            <th scope="col">Salary</th>
            <th scope="col">Staff</th>
            <th scope="col">Edit</th>
            <th scope="col">delete</th>
        </tr>
        </thead>
        <tbody>

        <#assign i = 1>
        <#list departments as department>
            <tr>
                <th scope="row">${i}</th>
                <td><a href="/departments/${department.id}">${department.name}</a></td>
                <td>----</td>
                <td><a href="/employees/department/${department.id}">View</a></td>
                <td><a href="/departments/edit/${department.id}">Edit</a></td>
                <td><a href="/departments/delete/${department.id}">Delete</a></td>
            </tr>
            <#assign i++>
        <#--Work only when array empty -->
        <#else >
            There are no departments in the database or you have problem with connect to REST server.
        </#list>

        </tbody>
    </table>
</@c.page>