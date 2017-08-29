package com.codigoquetzal.android.compara;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.SEARCH_SERVICE;


public class listaProductos extends Fragment {

    private RecyclerView mProductoRecyclerView;
    private ProductoAdapter mAdapter;

    public static listaProductos newInstance() {
        listaProductos fragment = new listaProductos();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Calls the OnCreateOptionsMenu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Busca dentro de 'view' el XML:producto_recycler_view
        View view = inflater.inflate(R.layout.fragment_lista_productos, container, false);
        mProductoRecyclerView = (RecyclerView) view
                .findViewById(R.id.producto_recycler_view);
        mProductoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private class ProductoHolder extends  RecyclerView.ViewHolder {

        private TextView mPrecioTextView;
        private ImageButton mProductoImageButton;
        private ImageButton mTiendaImageButton;
        private CheckBox mAgregarCheckBox;
        private Producto mProducto;

        public ProductoHolder(View itemView) {
            super(itemView);
            mPrecioTextView = (TextView)
                    itemView.findViewById(R.id.list_item_precio_textview);
            mProductoImageButton = (ImageButton)
                    itemView.findViewById(R.id.list_item_producto_image);
            mTiendaImageButton = (ImageButton)
                    itemView.findViewById(R.id.list_item_tienda_image);
            mAgregarCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_checkbox);

        }

        public void bindProducto(Producto producto) {
            mProducto = producto;
            mPrecioTextView.setText("$12");
            mProductoImageButton.setBackgroundColor(getResources().getColor(R.color.white));
            mTiendaImageButton.setBackgroundColor(getResources().getColor(R.color.white));
            mAgregarCheckBox.setChecked(true);
        }
    }

    private class ProductoAdapter extends RecyclerView.Adapter<ProductoHolder> {

        private List<Producto> mProductos;

        public ProductoAdapter(List<Producto> productos) {
            mProductos = productos;
        }

        // Re-define el metodo (Que se encuentra arriba)
        // Es llamado por RecyclerView cuando necesita mostrar un nuevo elemento.
        @Override
        public ProductoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.lista_item_producto, parent, false);
            return new ProductoHolder(view);
        }
        // Junta los datos que se desea mostrar
        @Override
        public void onBindViewHolder(ProductoHolder holder, int position) {
            Producto producto = mProductos.get(position);
            holder.bindProducto(producto);
        }


        @Override
        public int getItemCount() {
            return mProductos.size();
        }
    }

    public void updateUI() {
        ProductoLab productoLab = ProductoLab.get(getActivity());
        List<Producto> productos = productoLab.getProductos();

        mAdapter = new ProductoAdapter(productos);
        mProductoRecyclerView.setAdapter(mAdapter);
    }

    // No puede ser aplicado en activity!!
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.top_bar_menu, menu);
    }

}
