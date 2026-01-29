import { useEffect, useState } from "react";
import api from "../api/api";

export default function MealForm({ onCreated }) {
  const [users, setUsers] = useState([]);
  const [dishes, setDishes] = useState([]);
  const [userId, setUserId] = useState("");
  const [dishId, setDishId] = useState("");

  useEffect(() => {
    api.get("/users").then(r => setUsers(r.data));
    api.get("/dishes").then(r => setDishes(r.data));
  }, []);

  const submit = async (e) => {
    e.preventDefault();
    await api.post(`/meals?userId=${userId}&dishId=${dishId}`);
    onCreated();
  };

  return (
    <form onSubmit={submit}>
      <select onChange={e=>setUserId(e.target.value)}>
        <option>User</option>
        {users.map(u => <option key={u.id} value={u.id}>{u.username}</option>)}
      </select>

      <select onChange={e=>setDishId(e.target.value)}>
        <option>Dish</option>
        {dishes.map(d => <option key={d.id} value={d.id}>{d.name}</option>)}
      </select>

      <button>Create Meal</button>
    </form>
  );
}
