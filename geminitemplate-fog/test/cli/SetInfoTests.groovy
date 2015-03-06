import grails.test.AbstractCliTestCase

class SetInfoTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSetInfo() {

        execute(["set-info"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}
