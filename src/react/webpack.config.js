'use strict'
module.exports = {
    mode: "development",
    entry: "./src/schedule.js",
    output: {
	//path: "/home/belenot/programming/lessons/javascript/react/eatfood-frontend/bin",
	path: "/home/belenot/programming/projects/java/belenot/schedule-parser-web/src/main/webapp/resources/js/",
	filename: "bundle.js",
	sourceMapFilename: "bundle.map"
    },
    devtool: "#source-map",
    module: {
	rules: [
	    {
		test: /\.js$/,
		exclude: /(node_modules)/,
		loader: 'babel-loader',
		query: {
		    presets: ['env', 'react', 'stage-0']
		}
	    },
	    {
		test: /\.css$/,
		use: ['style-loader', 'css-loader', {
		    loader: 'postcss-loader',
		    options: {
			plugins: () => [require('autoprefixer')]
		    }
		}]
	    }
	]
    }
}
