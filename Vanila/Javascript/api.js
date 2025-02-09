document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        // Get the username and password from input fields
        const usernameInput = document.getElementById('username');
        const passwordInput = document.getElementById('password');
        const username = usernameInput.value.trim();  // Trim removes extra spaces
        const password = passwordInput.value.trim();

        // Validation: Check if username or password is too short
        if (username.length < 2 || password.length < 2) {
            document.getElementById('errorMessage').innerText = 'Username and password must be at least 2 characters long.';
            document.getElementById('successMessage').innerText = ''; // Clear success message

            // Turn the input fields red
            if (username.length < 2) usernameInput.style.backgroundColor = " red";
            if (password.length < 2) passwordInput.style.border = "2px solid red";

            return; // Stop execution (don't send the request)
        } else {
            // Reset border color if valid
            usernameInput.style.border = "";
            passwordInput.style.border = "";
        }

        // Create the data object to send to the server
        const data = `username=${username}&password=${password}`;

        // Send POST request to backend
        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: data
        })
        .then(response => response.text())
        .then(data => {
            if (data === "Login successful") {
                document.getElementById('successMessage').innerText = 'Login successful!';
                document.getElementById('errorMessage').innerText = ''; // Clear error message
            } else {
                document.getElementById('errorMessage').innerText = 'Invalid username or password.';
                document.getElementById('successMessage').innerText = ''; // Clear success message
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});
