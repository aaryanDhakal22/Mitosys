import React from "react";
import { Formik, Field, Form } from "formik";
interface Student {
  name: string;
  age: number;
  phone: number;
  id: string;
}
const Profile = ({
  student,
  backHandler,
  updateHandler,
  removeHandler,
}: {
  student: Student;
  backHandler: any;
  updateHandler: any;
  removeHandler: any;
}) => {
  const [currStudent, setCurrStudent] = React.useState<Student>(student);

  const handleSubmit = (values: any) => {
    const temp: Student = { ...values, id: currStudent.id };
    updateHandler(temp);
  };
  const handleRemoval = (event: any) => {
    backHandler();
    removeHandler.bind(null, currStudent.id)();
  };
  return (
    <div>
      <button onClick={backHandler}>Back</button>
      <button onClick={handleRemoval}>Delete</button>
      <h1>Contact Us</h1>
      <Formik
        initialValues={{ name: currStudent.name, age: currStudent.age, phone: currStudent.phone }}
        onSubmit={(values) => {
          handleSubmit(values);
        }}
      >
        <Form>
          <Field name="name" type="text" />
          <Field name="age" type="number" />
          <Field name="phone" type="number" />
          <button type="submit">Save</button>
        </Form>
      </Formik>
    </div>
  );

  // const [newStudent, setNewStudent] = React.useState<Student>(student);

  // const handleInputChange = (event: any) => {
  //   const target = event.target;
  //   const value = target.type === "checkbox" ? target.checked : target.value;
  //   const name = target.name;
  //   let temp: Student = { name: "", age: 0, phone: 0, id: "" };
  //   temp[name] = value as string |
  //   setNewStudent();
  // };
  // const handleSubmit = (event: any) => {};
  // return (
  //   <div>
  //     Profile<br></br>
  //     <button onClick={backHandler}>Back</button>
  //     <br></br>
  //     Name : <input type="text" name="name" value={newStudent.name} />
  //     <br />
  //     Age : <input type="text" name="age" value={newStudent.age} />
  //     <br />
  //     Phone : <input type="text" name="phone" value={newStudent.phone} />
  //     <br />
  //   </div>
  // );
};
export default Profile;
