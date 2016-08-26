# regression
this project contains regressions found in various projects existing in the SVN repos, and script to checkout and test project in order to find the failed test easily

1- firstly, run testRegressedVersion.sh to checkout and test the regressed version of your project, it will generate a file containing the failed test (failedTest.txt)
2- run testOtherVersion.sh to test other versions, it will only tests in the failedTest.txt and it will generate file that contains failed tests for a specific version (failedTest{Revision}.txt)


