package systems.vostok.taxi.drive.app.test

class Check {
    static void recheck(Integer checkAttempts, Closure closure) {
        Integer checkCounter = 0

        while(true) {
            try {
                closure.call()
                return
            } catch (AssertionError e) {
                if(checkCounter <= checkAttempts) {
                    checkCounter ++
                    sleep(200)
                } else {
                    throw new AssertionError(e)
                }
            }
        }
    }
}
