const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin')
// const VuetifyLoaderPlugin = require('vuetify-loader/lib/plugin')

module.exports = {
    mode: 'development',
    devtool: 'source-map',
    entry: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'main.js'),
    /*output: {
        filename: 'main.js',
        path: path.resolve(__dirname, 'dist'),
    },*/
    devServer: {
        contentBase: './dist',
        compress: true,
        port: 8000,
        allowedHosts: [
            'localhost:8080'
        ],
        stats: 'errors-only',
        clientLogLevel: 'error',
    },
    module: {
        rules: [
            {
                test: /\.m?js$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.css$/,
                use: [
                    'vue-style-loader',
                    // 'style-loader',
                    'css-loader'
                    /*{
                        loader: 'css-loader',
                        options: {
                            // css-loader set esModule: true as the default in v4.0.0
                            // https://github.com/webpack-contrib/css-loader/releases/tag/v4.0.0
                            // Doesn't work unless this disabled
                            esModule: true
                        }
                    },*/
                ]
            },
            {
                test: /\.s([ca])ss$/,
                use: [
                    'vue-style-loader',
                    // 'style-loader',
                    'css-loader',
                    {
                        loader: 'sass-loader',

                        options: {
                            implementation: require('sass'),
                            sassOptions: {
                                indentedSyntax: true // optional
                            },
                        },
                    },
                ],
            },
        ]
    },
    plugins: [
        new VueLoaderPlugin(),
        // new VuetifyLoaderPlugin()
    ],

    resolve: {
        modules: [
            path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
            path.join(__dirname, 'node_modules'),
        ],
    }
};