# SpinnerSearch
library doing research in spinner.
</br>
</br>



# Requirements
SpinnerSearch requires at minimum Android 4.0.3 (API level 15).
</br>
</br>


# Gradle Dependency

## Repository
The Gradle dependency is available via Maven. Maven is the default Maven repository used by Android Studio.
</br>

## Add repository
<pre><code>
repositories {
    maven {
          url  "https://dl.bintray.com/rod120/SpinnerSearch"
          }
}
</code></pre>



## Add dependency

#### Gradle:
<pre><code>
    compile 'com.github.rodlibs:spinnerSearch:1.0'
</code></pre>


#### Maven:
```xml
 <dependency>
  <groupId>com.github.rodlibs</groupId>
  <artifactId>spinnerSearch</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```
</br>
</br>


# Sample usage
#### .java

###### Create a list with the List object in the Group and Item pattern. Then add the list of items and context within SpinnerSearch.
<pre><code>
        List<Item> listItem = new ArrayList<>();
        listItem.add(new Item("Group 1","item 1"));
        listItem.add(new Item("Group 1","item 2"));
        listItem.add(new Item("Group 1","item 3"));
        listItem.add(new Item("Group 2","Item test 1"));
        listItem.add(new Item("Group 2","Item test 2"));
        listItem.add(new Item("Group 2","Item test 3"));
        listItem.add(new Item("Group 3","item 1"));
        listItem.add(new Item("Group 3","item 2"));
        listItem.add(new Item("Group 3","item 3"));

        SpinnerSearch spinner = new SpinnerSearch(MainActivity.this,listItem);
</code></pre>


###### Listener that returns the selected item.
<pre><code>
    spinner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int i, Item item) {
            }
        });
</code></pre>


###### Displayed the component.
<pre><code>
    spinner.show();
</code></pre>



#### .kt

###### Add bitmap, background color, release background color change and open activity for image editing.
<pre><code>
   ImageEdCrop.targetBitmap(myBitmap)
                .photoBackgroundColor(Color.parseColor("#000000"))
                .changeBackgroundColor(true)
                .start(this@MainActivity);
</code></pre>


###### Receives the return of the edited image.
<pre><code>
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_IMAGE && resultCode == RESULT_OK){
            var byteArray: ByteArray?  = data?.getByteArrayExtra(Constants.RETURN_IMAGE_BITMAP)
            if (byteArray != null) {
                var bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray?.size)
            }
        }
</code></pre>
</br>




# Api Methods
<pre><code>
  targetBitmap(myBitmap)  -->> Receives a Bitmap.
</code></pre>

<pre><code>
  photoBackgroundColor(myColor)  -->> You receive an int. This method will set the background color of the image.
</code></pre>

<pre><code>
  changeBackgroundColor(true)  -->> You receive a boolean. This method will allow if user can change the background color.
</code></pre>

<pre><code>
  start();  -->> Receive an Activity. This method will send the data to the edit image screen.
</code></pre>
</br>





# License
<pre><code>
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</code></pre>

