import React from "react";
import ID from "./ID";
import Search from "./Search";
import Profile from "./Profile";
import initialValue from "./jsondata";
interface Student {
  name: string;
  age: number;
  phone: number;
  id: string;
}
interface Profile {
  view: boolean;
  payload?: string;
}

const StudentCard = () => {
  const [studentList, setStudentList] = React.useState<Student[]>(initialValue);
  const [profileView, setProfileView] = React.useState<boolean>(false);
  const [profileViewSelected, setProfileViewSelected] = React.useState<Student>({
    name: "",
    age: 0,
    phone: 0,
    id: "",
  });

  const handleSearch = (term: string) => {
    setStudentList(
      initialValue.filter((item: Student) => {
        return item.name.toLowerCase().includes(term.toLowerCase());
      })
    );
  };
  const handleProfileBack = () => {
    setProfileView(false);
  };
  const handleProfileView = (id: string) => {
    setProfileViewSelected(
      studentList.filter((item: Student) => {
        return item.id === id;
      })[0]
    );
    console.log(profileViewSelected);
    setProfileView(true);
  };
  return (
    <>
      <h1>Students</h1>
      <Search searchHandle={handleSearch} />
      <br />
      <br />
      <div className="row">
        {profileView ? (
          <Profile student={profileViewSelected} backHandler={handleProfileBack} />
        ) : (
          studentList.map((student: Student) => {
            return <ID student={student} key={student.id} profileView={handleProfileView} />;
          })
        )}
      </div>
    </>
  );
};
export default StudentCard;
