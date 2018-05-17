def call(Map params) {
    def version = params.version
    sh "git checkout -b release/$version"

    if (params.performReleaseBranch) {
        params.performReleaseBranch(version)
    }

    sh "git checkout develop"
    sh "git merge --ff-only release/$version"
    sh "git checkout master"
    sh "git merge --ff-only release/$version"
    sh "git branch -D release/$version"
    sh "git checkout develop"
    sh "git push --all && git push --tags"
}
