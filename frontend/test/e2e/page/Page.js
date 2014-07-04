'use strict'

var Page = function() {

  this.clickLink = function(linkText) {
    element(by.linkText(linkText)).click();
  };

};

module.exports = new Page();
