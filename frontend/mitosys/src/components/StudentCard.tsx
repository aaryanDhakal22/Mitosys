import React from "react";
import ID from "./ID";
import Search from "./Search";
import Profile from "./Profile";
import initialValue from "./jsondata";
import AddStudent from "./AddStudent";
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
const empty_student = {
  name: "",
  age: 0,
  phone: 0,
  id: "",
};
let initials = initialValue;
interface View {
  view: "addview" | "profileView" | "listView";
}
const StudentCard = () => {
  const [studentList, setStudentList] = React.useState<Student[]>(initials);
  const [view, setView] = React.useState<View>({ view: "listView" });
  const [profileViewSelected, setProfileViewSelected] = React.useState<Student>(empty_student);
  const handleUpdate = (payload: Student) => {
    console.log(payload);
    const newList = studentList.map((item) => {
      if (item.id === payload.id) {
        return payload;
      }
      return item;
    });
    console.log(newList);
    setStudentList(newList);
  };
  const handleSearch = (term: string) => {
    setStudentList(
      initials.filter((item: Student) => {
        return item.name.toLowerCase().includes(term.toLowerCase());
      })
    );
  };
  const handleView = (view: "addview" | "profileView" | "listView") => {
    setView({ view: view });
  };
  const handleProfileView = (id: string) => {
    setProfileViewSelected(
      studentList.filter((item: Student) => {
        return item.id === id;
      })[0]
    );
    console.log(profileViewSelected);
    handleView("profileView");
  };

  const handleRemove = (id: string) => {
    initials = initials.filter((item: Student) => {
      return item.id !== id;
    });
    setStudentList(initials);
  };
  const handleAdd = () => {};
  return (
    <>
      <h1>Students</h1>
      <Search searchHandle={handleSearch} />
      <br />
      <button onClick={handleProfileView.bind(null, "addView")}>Add</button>
      <br />
      <div className="row">
        if(view.view === "profileView"){" "}
        {
          <Profile
            student={profileViewSelected}
            backHandler={handleView.bind(null, "listView")}
            updateHandler={handleUpdate}
            removeHandler={handleRemove}
          />
        }
        else if (view.view === "listView")
        {studentList.map((student: Student) => {
          return <ID student={student} key={student.id} profileView={handleProfileView} />;
        })}
        else if (view.view === "addView")
        {<AddStudent addHandler={handleAdd} backHandler={handleView.bind(null, "listView")} />}
      </div>
    </>
  );
};
export default StudentCard;
