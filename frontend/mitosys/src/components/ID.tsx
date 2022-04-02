interface Student {
  name: string;
  age: number;
  phone: number;
  id: string;
}
const ID = ({ student }: { student: Student }) => {
  return (
    <div className="col-3">
      <a href={student.name}>
        <div className="bg-dark text-light">
          {student.name}
          <ul className="list-group list-group-flush">
            <li className="list-group-item">{student.age}</li>
            <li className="list-group-item">{student.phone}</li>
          </ul>
        </div>
      </a>
    </div>
  );
};
export default ID;
