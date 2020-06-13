import React, { Component } from 'react';
import logo from './logo.svg';
import axios from 'axios';
import './App.css';

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

function App() {
  return (
    <div className="App">
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}
      <h1>Welcome to Travel Corps</h1>
        <form action="http://localhost:8080/TravelCorps/loginVolunteer" method="post" modelAttribute="user">
          <fieldset>
              <legend>Volunteer Login Here</legend>
              <div>
                  <label for="userName">Username: </label>
                  <div>
                      <input path="userName" />
                      <p><errors path="userName" class="error" /></p>
                  </div>
              </div>
              <div>
                  <label for="password">Password</label>
                  <div>
                      <input path="password" type="password" />
                      <p><errors path="password" class="error" /></p>
                  </div>
              </div>
              <input type="submit" value="Login" />
          </fieldset>
        </form>
        <a href="${pageContext.request.contextPath}/volunteerRegistration">Volunteer Registration</a>
    </div>
  );
}


export default App;
