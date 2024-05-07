// Function to open the login form
function openLoginForm() {
    document.getElementById('loginFormContainer').style.display = 'block';
}

// Function to close the login form
function closeLoginForm() {
    document.getElementById('loginFormContainer').style.display = 'none';

}

// Function to handle form submission (for demo purposes)
document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();
    console.log('Login form submitted');
})

// Function to open the register form
function openRegisterForm() {
    document.getElementById('registerFormContainer').style.display = 'block';
}

// Function to close the register form
function closeRegisterForm() {
    document.getElementById('registerFormContainer').style.display = 'none';
}

// Function to handle form submission (for demo purposes)
document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission
    // Add your registration logic here (e.g., sending AJAX request)
    console.log('Register form submitted');
});