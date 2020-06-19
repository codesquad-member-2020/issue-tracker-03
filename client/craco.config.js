const path = require('path');

module.exports = {
  webpack: {
    alias: {
      '@': path.resolve(__dirname, 'src'),
      '@Components': path.resolve(__dirname, 'src/components'),
      '@Containers': path.resolve(__dirname, 'src/containers'),
      '@Modules': path.resolve(__dirname, 'src/modules'),
      '@Pages': path.resolve(__dirname, 'src/pages'),
      '@Routers': path.resolve(__dirname, 'src/routers'),
      '$Utils': path.resolve(__dirname, 'src/utils'),
      '$Libs': path.resolve(__dirname, 'src/libs'),
      '$API': path.resolve(__dirname, 'src/api'),
    }
  },
  jest: {
    configure: {
      moduleNameMapper: {
        '^@(.*)$': '<rootDir>/src$1'
      }
    }
  }
};