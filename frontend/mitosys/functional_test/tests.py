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

if __name__ == "__main__":
    unittest.main(warnings="ignore")