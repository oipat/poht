exports.config = {
	allScriptsTimeout: 11000,

  specs: [
		'test/e2e/*.js'
	],

	capabilities: {
		'browserName': 'phantomjs',
		'phantomjs.binary.path': './node_modules/phantomjs/bin/phantomjs',
		'phantomjs.cli.args': ''
	},

	baseUrl: 'http://localhost:9000',

	framework: 'jasmine',

	jasmineNodeOpts: {
		defaultTimeoutInterval: 30000,
		isVerbose: true,
		showColors: true,
		includeStackTrace: true
	},

	rootElement: 'body'
};
