module.exports = {
    // transform: {
    //     "^.+\\.tsx?$": "ts-jest",
    // },
    testRegex: "(/tests/.*|(\\.|/)(test|spec))\\.(jsx?|tsx?)$",
    // testRegex: "__tests__/parseAndPrint-test.ts$",
    testPathIgnorePatterns: ["/dist/", "/node_modules/"],
    coveragePathIgnorePatterns: [ "dist" ],
    moduleFileExtensions: ["ts", "tsx", "js", "jsx", "json", "node"],
    collectCoverage: true,
};
