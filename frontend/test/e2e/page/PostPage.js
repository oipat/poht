'use strict'

var Page = require('./Page.js');

var PostPage = function() {

  this.verify = function() {
    expect(element(by.id("post")).isDisplayed()).toBeTruthy();
  };

  this.submitPost = function(subject, body) {
    element(by.name("subject")).sendKeys(subject);
    element(by.name("body")).sendKeys(body);
    element(by.name("submit")).click();
  };
};

PostPage.prototype = Page;

module.exports = new PostPage();
