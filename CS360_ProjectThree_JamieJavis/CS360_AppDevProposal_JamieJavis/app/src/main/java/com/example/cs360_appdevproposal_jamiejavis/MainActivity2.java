package com.example.cs360_appdevproposal_jamiejavis;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView dataRecyclerView;
    private InventoryAdapter inventoryAdapter;
    private InventoryDatabase inventoryDatabase;
    private EditText enterItemEditText, enterNumberEditText, itemIDEditText;
    private Button addDataButton, editItemButton, updateListButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dataRecyclerView = findViewById(R.id.dataRecyclerView);
        dataRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        inventoryDatabase = new InventoryDatabase(this);
        Cursor cursor = inventoryDatabase.getAllItems();
        inventoryAdapter = new InventoryAdapter(this, cursor);
        dataRecyclerView.setAdapter(inventoryAdapter);

        // initialize UI elements
        enterItemEditText = findViewById(R.id.enterItem);
        enterNumberEditText = findViewById(R.id.enterNumber);
        itemIDEditText = findViewById(R.id.itemID);
        addDataButton = findViewById(R.id.addDataButton);
        editItemButton = findViewById(R.id.editItemButton);
        updateListButton = findViewById(R.id.updateList);
        deleteButton = findViewById(R.id.deleteButton);

        // set for addDataButton
        addDataButton.setOnClickListener(v -> {
            String itemName = enterItemEditText.getText().toString();
            int quantity = Integer.parseInt(enterNumberEditText.getText().toString());
            // add item to database
            inventoryDatabase.addItem(itemName, quantity);
            // Update RecyclerView
            updateRecyclerView();
        });

        // set for editItemButton
        editItemButton.setOnClickListener(v -> {
            int id = Integer.parseInt(itemIDEditText.getText().toString());
            int newQuantity = Integer.parseInt(enterNumberEditText.getText().toString());
            // update item quantity in database
            inventoryDatabase.updateItem(id, newQuantity);
            // update RecyclerView
            updateRecyclerView();
        });

        // set for updateListButton
        updateListButton.setOnClickListener(v -> updateRecyclerView());

        // set for deleteButton
        deleteButton.setOnClickListener(v -> {
            int id = Integer.parseInt(itemIDEditText.getText().toString());
            // delete item from database
            inventoryDatabase.deleteItem(id);
            // update RecyclerView
            updateRecyclerView();
        });
    }

    // update RecyclerView with new data from database
    private void updateRecyclerView() {
        Cursor cursor = inventoryDatabase.getAllItems();
        inventoryAdapter.swapCursor(cursor); // update cursor in adapter
    }
}




