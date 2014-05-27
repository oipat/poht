'use strict'


describe('BlogiApp', function () {

	 it('should have visible login button', function() {
		browser.get('/');
		expect(element(by.xpath('//button[@id="login-submit"]')).isDisplayed())
			.toBeTruthy();
	 });
});
