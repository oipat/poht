'use strict'

var Page = require('./Page.js');

var FrontPage = function() {

  this.verify = function() {
    expect(element(by.xpath('//button[@id="login-submit"]')).isDisplayed())
      .toBeTruthy();
  };
};

FrontPage.prototype = Page;

module.exports = new FrontPage();
