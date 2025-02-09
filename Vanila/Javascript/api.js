document.addEventListener('DOMContentLoaded', function() {
    // Get the form and handle the login logic
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent the form from submitting the traditional way
        
        // Get the username and password from input fields
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        // Create the data object to send to the server
        const data = `username=${username}&password=${password}`;

        //console.log("Sending request with data:", data);


        // Make the POST request to the backend server
        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: data
        })
        .then(response => response.text())
        .then(data => {
            // If login is successful, show a success message, otherwise show an error
            if (data === "Login successful") {
                // Show success message
                document.getElementById('successMessage').innerText = 'Login successful!';
                document.getElementById('errorMessage').innerText = ''; // Clear any error message
            } else {
                // Show error message
                document.getElementById('errorMessage').innerText = 'Invalid username or password.';
                document.getElementById('successMessage').innerText = ''; // Clear any success message
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});
