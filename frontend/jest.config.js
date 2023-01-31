const path = require('path')

module.exports = {
  rootDir: path.resolve(__dirname),
  moduleFileExtensions: [
    'js',
    'vue',
    'html'
  ],
  moduleNameMapper: {
    '^@/(.*)$': '<rootDir>/src/$1',
  },
  transform: {
    '^.+\\.js$': '<rootDir>/node_modules/babel-jest',
    '.*\\.vue$': '<rootDir>/node_modules/@vue/vue3-jest',
  },
  transformIgnorePatterns: [
    "/node_modules/(?!vue-multiselect)",
    "/node_modules/(?!vue-multiselect/src/Multiselect.vue)",
    "/node_modules/(?!axios)",
      "/node_modules/",
      "/node_modules/axios"
  ],
  testPathIgnorePatterns: [
    "<rootDir>/node_modules/*",
      "<rootDir>/node_modules/axios"
  ],
  modulePaths: [
    "<rootDir>"
  ],
  testMatch: [
    '<rootDir>/tests/unit/*.spec.js'
  ],
  testEnvironment: 'jsdom'
}


