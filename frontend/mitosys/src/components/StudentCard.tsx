import React from "react";
import ID from "./ID";
import Search from "./Search";
import initialValue from "./jsondata";
interface Student {
  name: string;
  age: number;
  phone: number;
  id: string;
}

const StudentCard = () => {
  const [studentList, setStudentList] = React.useState<Student[]>(initialValue);

  const handleSearch = (term: string) => {
    setStudentList(
      initialValue.filter((item: Student) => {
        return item.name.toLowerCase().includes(term.toLowerCase());
      })
    );
  };
  return (
    <>
      <h1>Students</h1>
      <Search searchHandle={handleSearch} />
      <br />
      <br />
      <div className="row">
        {studentList.map((student: Student) => {
          return <ID student={student} key={student.id} />;
        })}
      </div>
    </>
  );
};
export default StudentCard;
