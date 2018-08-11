Feature: Client flow functionality:
  - scan file system periodically
  - save new files to the database
  - notify subscribed beans

  Scenario: Basic usage of file loader
    Given There is a file "test1.fin" containing a SWIFT message
    And File "test1.fin" has not been processed yet
    And There is a bean implementing org.springframework.context.ApplicationListener subscribed to the service
    When File System Scanning Process is successfully executed
    Then There is 1 message in the database
    And Subscribed bean received a message of type cbs.ipsagent.common.NewRawMessagesEvent
    And File "test1.fin" persists