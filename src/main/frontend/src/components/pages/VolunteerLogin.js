import React, { Component } from 'react'

export default class VolunteerLogin extends Component {
    render() {
        return (
            <div>
                <h1>Welcome to Travel Corps</h1>
                    <form:form action="${pageContext.request.contextPath}/loginVolunteer" method="post" modelAttribute="user">
                    <fieldset>
                        <legend>Volunteer Login Here</legend>
                        <p style="color:red;">${errorMessage}</p>
                        <div>
                            <label for="userName">Username: </label>
                            <div>
                                <form:input path="userName" />
                                <p><form:errors path="userName" class="error" /></p>
                            </div>
                        </div>
                        <div>
                            <label for="password">Password</label>
                            <div>
                                <form:input path="password" type="password" />
                                <p><form:errors path="password" class="error" /></p>
                            </div>
                        </div>
                        <input type="submit" value="Login">
                    </fieldset>
                    </form:form>
                    <a href="${pageContext.request.contextPath}/volunteerRegistration">Volunteer Registration</a>
            </div>
        )
    }
}
