from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.common.exceptions import WebDriverException
import time
import unittest
class TestStudentProfile(unittest.TestCase):

    def setUp(self) -> None:
        self.browser = webdriver.Firefox()
        self.browser.get('localhost:3000')

    def tearDown(self) -> None:
        self.browser.quit()

    def test_react_is_setup_properly(self):
        self.assertIn("Mitosys",self.browser.title)

    def test_search_to_read_low_details(self):
        # Jo sees that there is a list of clickable student ids 
        a_tags = self.browser.find_elements(By.TAG_NAME,'a')
        self.assertNotEqual(len(a_tags),0)
        # He sees that there is a aaryan guy
        a_tags[0].find_element()
        # He then proceeds to read the smaller details 

        # He Clicks on the aaryan id and is presented with a larger pop up with the full account details

        # He then clicks on the personal 

        # And is presented with the entire personal details 

        # He then clicks the cross button and is then sent back to the previous page


        pass
    def test_add_new_user_and_show(self):
        pass
    
    def test_delete_old_users(self):
        pass




if __name__ == "__main__":
    unittest.main(warnings="ignore")