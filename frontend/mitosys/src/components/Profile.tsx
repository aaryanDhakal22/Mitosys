interface Student {
  name: string;
  age: number;
  phone: number;
  id: string;
}
const Profile = ({ student, backHandler }: { student: Student; backHandler: any }) => {
  return (
    <div>
      Profile
      <button onClick={backHandler}>Back</button>
      <h1>{student.name}</h1>
    </div>
  );
};
export default Profile;
