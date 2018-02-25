<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="post" action="addContact.html">

    <table>
        <tr>
            <td><form:label path="firstname">First Name</form:label></td>
            <td><form:input path="firstname" /></td> 
        </tr>
        <tr>
            <td><form:label path="lastname">Last Name</form:label></td>
            <td><form:input path="lastname" /></td>
        </tr>
        <tr>
            <td><form:label path="lastname">Email</form:label></td>
            <td><form:input path="email" /></td>
        </tr>
        <tr>
            <td><form:label path="lastname">Telephone</form:label></td>
            <td><form:input path="telephone" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add Contact"/>
            </td>
        </tr>
    </table>  

</form:form>