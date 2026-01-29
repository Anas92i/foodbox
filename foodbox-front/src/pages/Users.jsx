import { useEffect, useState } from "react";
import api from "../api/api";
import UserForm from "../components/UserForm";

export default function Users() {
  const [users, setUsers] = useState([]);

  const load = async () => {
    const res = await api.get("/users");
    setUsers(res.data);
  };

  useEffect(() => { load(); }, []);

  return (
    <div>
      <h1>Users</h1>
      <UserForm onCreated={load} />
      <ul>
        {users.map(u => (
          <li key={u.id}>{u.username} — {u.email}</li>
        ))}
      </ul>
    </div>
  );
}
