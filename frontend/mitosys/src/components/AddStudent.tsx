import React from "react";
import { Formik, Field, Form } from "formik";
interface Student {
  name: string;
  age: number;
  phone: number;
  id: string;
}
const empty_student = {
  name: "",
  age: 0,
  phone: 0,
  id: "",
};
const AddStudent = ({ addHandler, backHandler }: { addHandler: any; backHandler: any }) => {
  const [currStudent, setCurrStudent] = React.useState<Student>(empty_student);

  const handleSubmit = (values: any) => {
    const temp: Student = { ...values, id: currStudent.id };
    addHandler(temp);
  };
  return (
    <div>
      <button onClick={backHandler}>Back</button>
      <h1>Add</h1>
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
};
export default AddStudent;
