'use strict'

var frontPage;
var postPage;

beforeEach(function() {
	frontPage = require('./page/FrontPage.js');
	postPage = require('./page/PostPage.js')
});

describe('Frontpage', function () {

	it('should have visible login button', function() {
		browser.get('/');
		frontPage.verify();
	});

});

describe('Postingpage', function() {
	beforeEach(function() {
		browser.get('/');
		frontPage.verify();
	});

	it('should be able to post a new post', function() {
		frontPage.clickLink('Post');
		postPage.verify();
		postPage.submitPost('subject', 'body');
		postPage.clickLink('Frontpage');
		expect(element(by.xpath('//div[contains(@class, "post")]/a/h1[contains(text(), "subject")]'))
			.isDisplayed()).toBeTruthy();
	});
});
