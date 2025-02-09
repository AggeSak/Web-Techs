const { test, expect } = require('@playwright/test');

test('Login Test2', async ({ page }) => {
    test.setTimeout(60000); // Set timeout to 60s to prevent timeouts

    // Navigate to the login page
    await page.goto('file:///C:/Users/User/Desktop/DnD%20Web/HTML-CSS/Vanila/api.html');  // Change URL if needed

    // Wait for the page to fully load
    await page.waitForLoadState('domcontentloaded');

    // Ensure the username input exists before filling
    await page.waitForSelector('input[name="username"]', { timeout: 5000 });
    await page.fill('input[name="username"]', 'JnDoe');

    // Ensure the password input exists before filling
    await page.waitForSelector('input[name="password"]', { timeout: 5000 });
    await page.fill('input[name="password"]', 'mswod123');

    // Ensure the login button exists before clicking
    await page.waitForSelector('button[type="submit"]', { timeout: 5000 });
    await page.click('button[type="submit"]');

    // Wait for a response (check if login was successful)
    await page.waitForSelector('#errorMessage', { timeout: 5000 }); // Adjust selector if needed
    const successText = await page.textContent('#errorMessage');

    // Expect the success message to appear
    expect(successText).toContain('Invalid username or password');
});
